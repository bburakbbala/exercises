using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IDoctorRepository : IRepositoryAsync<Doctor>
    {
        void Update(Doctor doctor);
    }
}
