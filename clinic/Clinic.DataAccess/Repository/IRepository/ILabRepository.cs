using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ILabRepository : IRepositoryAsync<Lab>
    {
        void Update(Lab lab);
    }
}
