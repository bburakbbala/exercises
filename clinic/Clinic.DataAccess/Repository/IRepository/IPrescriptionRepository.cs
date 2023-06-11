using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IPrescriptionRepository : IRepositoryAsync<Prescription>
    {
        void Update(Prescription prescription);
    }
}